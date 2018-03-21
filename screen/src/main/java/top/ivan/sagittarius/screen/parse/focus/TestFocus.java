package top.ivan.sagittarius.screen.parse.focus;

import top.ivan.sagittarius.screen.parse.UnSupportFocusException;
import top.ivan.sagittarius.screen.utils.Calculator;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/25
 */
public class TestFocus implements Focus, ExportFocusHandle {

    private static final long serialVersionUID = -8332613244019590915L;
    private FocusManager manager;

    /**
     * @param src
     * @param target
     * @param key
     * @return
     * @throws Exception
     * @targetType {focus}[{realTarget}]
     * @keyType {test expression}[{realKey}]
     * @example src: {'id':20171119,'info':'hello world!'} target: json[info] key: @id@(math)=20171119[info]
     * @@{value}@ take JsonValue: @id@ ==> 20171119
     * @#{value}# just take value #id# ==> id
     * @${value}$ take replace value,just like String::replaceAll(regex,"$1"),example: $\{'id':(\d+).*$ ==> 20171119
     * @$ $==> {'id':20171119,'info':'hello world!'}
     */
    @Override
    public String peek(String src, String target, String key) throws Exception {

        KeyOrder keyOrder = KeyOrder.getKeyOrder(key);
        String srcType = keyOrder.getOriginal();
        if (keyOrder.pass(peekKey(src, srcType), keyOrder.getOrder())) {
            String exportTarget = ExportFocusHandle.getExportTarget(target);
            Focus focus = ExportFocusHandle.getExportFocus(target, manager);
            return focus.peek(src, exportTarget, keyOrder.getRealKey());
        }
        throw new UnSupportFocusException("can not pass TestFocus test");
    }

    @Override
    public void setManager(FocusManager manager) {
        this.manager = manager;
    }

    public static String nullValue(Object src) {
        if (null == src || "".equals(src.toString().trim())) {
            return null;
        }
        return src.toString();
    }

    public static String notNullValue(Object src) {
        if (null == src) {
            return "";
        } else {
            return src.toString();
        }
    }

    private String peekKey(String src, String srcType) throws Exception {
        if (srcType.matches("^@[\\s\\S]*@$")) {
            return JsonFocus.takeJsonValue(src, srcType.replace("@", ""));
        } else if (srcType.matches("^#[\\s\\S]*#$")) {
            return srcType.replace("#", "");
        } else if (srcType.matches("^\\$[\\s\\S]*\\$$")) {
            return src.replaceAll(srcType.substring(1, srcType.length() - 1), "$1");
        } else if ("$".equals(srcType)) {
            return src;
        } else {
            return srcType;
        }
    }

    private static class KeyOrder {
        static final String REGEX = "^(@[\\s\\S]+?@|#[\\s\\S]+?#|\\$[\\s\\S]+?\\$|\\$)([\\s\\S]*?)>>\\[([\\s\\S]*)\\]$";
        private static Lexicon<OrderInvoker> lexicon = new Lexicon<>(null, null);

        {
            lexicon.put(">", (a, b) -> a.compareTo(b) > 0);
            lexicon.put("<", (a, b) -> a.compareTo(b) < 0);
            lexicon.put("=", (a, b) -> a.compareTo(b) == 0);
            lexicon.put("matches_", String::matches);
            lexicon.put("contains_", String::contains);
            lexicon.put("equals_", String::equalsIgnoreCase);
            lexicon.put("(math)>", (a, b) -> Calculator.conversion(a) > Calculator.conversion(b));
            lexicon.put("(math)<", (a, b) -> Calculator.conversion(a) < Calculator.conversion(b));
            lexicon.put("(math)=", (a, b) -> Calculator.conversion(a) == Calculator.conversion(b));
        }

        private String original;
        private String order;
        private String key;

        KeyOrder(String key) throws UnSupportFocusException {
            if (!isKeyOrder(key)) {
                throw new UnSupportFocusException(String.format("can not parse key order with:\"%s\"", key));
            }
            this.original = key.replaceAll(REGEX, "$1");
            this.order = key.replaceAll(REGEX, "$2");
            this.key = key.replaceAll(REGEX, "$3");
        }

        String getRealKey() {
            return key;
        }

        String getOrder() {
            return order;
        }

        String getOriginal() {
            return original;
        }

        static KeyOrder getKeyOrder(String keyOrder) throws UnSupportFocusException {
            return new KeyOrder(keyOrder);
        }

        static boolean isKeyOrder(String keyOrder) {
            return keyOrder.matches(REGEX);
        }

        boolean pass(String original, String order) throws UnSupportFocusException {
            boolean upset = order.startsWith("!");
            if (upset) {
                order = order.substring(1);
            }
            Lexicon<OrderInvoker> node = lexicon.get(order);
            if (null == node) {
                throw new UnSupportFocusException(String.format("no suitable test order with:\"%s\"", order));
            }
            OrderInvoker invoker = node.getValue();
            String key = order.replace(node.getPath(), "");
            return upset != invoker.test(original, key);
        }

        static void registerOrder(String order, OrderInvoker invoker) {
            lexicon.put(order, invoker);
        }

        private interface OrderInvoker {
            boolean test(String src, String key);
        }

        private static class Lexicon<T> {
            Character c;
            Lexicon parent;
            T value;
            Map<Character, Lexicon<T>> next;

            Lexicon(Lexicon parent, Character c) {
                this.c = c;
                this.parent = parent;
            }

            void put(String cs, T obj) {
                if (next == null) {
                    next = new LinkedHashMap<>();
                }
                if (cs.length() > 0) {
                    Lexicon<T> lex = next.get(cs.charAt(0));
                    if (lex == null) {
                        lex = new Lexicon<T>(this, cs.charAt(0));
                        next.put(cs.charAt(0), lex);
                    }
                    lex.put(cs.substring(1), obj);
                } else {
                    value = obj;
                }
            }

            Lexicon<T> get(String cs) {
                if (cs != null && cs.length() > 0 && next != null) {
                    Lexicon<T> lex = next.get(cs.charAt(0));
                    if (lex != null) {
                        return lex.get(cs.substring(1));
                    }
                }
                return null == value ? null : this;
            }

            String getPath() {
                if (parent != null) {
                    return parent.getPath() + notNullValue(c);
                }
                return notNullValue(c);
            }

            T getValue() {
                return value;
            }

        }

    }

}
