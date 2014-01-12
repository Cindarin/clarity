package clarity.model;

import clarity.decoder.Util;

import com.google.protobuf.ByteString;

public class StringTable {

    private final String name;
    private final String[] names;
    private final ByteString[] values;

    public StringTable(String name, int maxEntries) {
        this.name = name;
        this.names = new String[maxEntries];
        this.values = new ByteString[maxEntries];
    }

    public void set(int index, String name, ByteString value) {
        if (index < names.length) {
            this.names[index] = name;
            this.values[index] = value;
        } else {
            throw new RuntimeException("out of index (" + index + "/" + names.length + ")");
        }
    }

    public ByteString getValueByIndex(int index) {
        return values[index];
    }

    public String getNameByIndex(int index) {
        return names[index];
    }

    public ByteString getValueByName(String key) {
        for (int i = 0; i < names.length; i++) {
            if (key.equals(names[i])) {
                return values[i];
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        String[] convValues = new String[names.length];
        for (int i = 0; i < names.length; i++) {
            convValues[i] = values[i] == null ? null : Util.convertByteString(values[i], "ISO-8859-1");
        }
        for (int i = 0; i < names.length; i++) {
            if (names[i] == null) {
                continue;
            }
            buf.append(i);
            buf.append(":");
            buf.append(names[i]);
            buf.append(" = ");
            buf.append(convValues[i]);
            buf.append("\r\n");
        }
        return buf.toString();
    }

}
