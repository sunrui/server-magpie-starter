package com.honeysense.magpie.utils.format;

public class MagpieJsonFormat {
    public static String format(String json) {
        int depth = 0;

        json = json.replace("\n", "");
        json = json.replace("\r", "");

        boolean quotationOpen = false;
        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);
            char cBefore = '\u0000';
            if (i > 0)
                cBefore = json.charAt(i - 1);

            if (c == '"' && cBefore != '\\') {
                quotationOpen = !quotationOpen;
            } else if (c == ' ' || c == '\u0009') {
                if (quotationOpen)
                    continue;

                json = new StringBuilder(json).deleteCharAt(i).toString();
                i--;
            }
        }

        char lastC = '\u0000';
        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);
            char nextC = '\u0000';
            if (i + 1 < json.length()) {
                nextC = json.charAt(i + 1);
            }

            if (c == '{' || c == '[') {
                if (lastC == ' ') {
                    String newLine = getNewLine(depth);
                    json = addString(json, i - 1, newLine);
                    i += newLine.length();
                }

                depth++;
                json = addString(json, i, getNewLine(depth));
            } else if (c == '}' || c == ']') {
                depth--;
                String newLine = getNewLine(depth);
                json = addString(json, i - 1, newLine);
                i += newLine.length();
            } else if (c == ':') {
                json = addString(json, i, " ");
            } else if (c == ',') {
                if (nextC != '{' && nextC != '[') {
                    json = addString(json, i, getNewLine(depth));
                } else {
                    json = addString(json, i, " ");
                }
            }

            lastC = c;
        }

        return json;
    }

    private static String addString(String base, int afterIndex, String toAdd) {
        return base.substring(0, afterIndex + 1) + toAdd + base.substring(afterIndex + 1);
    }

    private static String repeat(String string, int times) {
        return String.valueOf(string).repeat(Math.max(0, times));
    }

    private static String getNewLine(int depth) {
        final int SPACES_PER_DEPTH = 0;
        final int TABS_PER_DEPTH = 1;

        return "\n" + repeat(" ", SPACES_PER_DEPTH * depth) + repeat("\u0009", TABS_PER_DEPTH * depth);
    }
}
