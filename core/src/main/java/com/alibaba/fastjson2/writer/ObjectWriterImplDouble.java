package com.alibaba.fastjson2.writer;

import com.alibaba.fastjson2.JSONWriter;

import java.lang.reflect.Type;
import java.text.DecimalFormat;

final class ObjectWriterImplDouble
        extends ObjectWriterPrimitiveImpl {
    static final ObjectWriterImplDouble INSTANCE = new ObjectWriterImplDouble(null);

    private final DecimalFormat format;

    public ObjectWriterImplDouble(DecimalFormat format) {
        this.format = format;
    }

    @Override
    public void writeJSONB(JSONWriter jsonWriter, Object object, Object fieldName, Type fieldType, long features) {
        if (object == null) {
            jsonWriter.writeNull();
            return;
        }
        jsonWriter.writeDouble(((Double) object).doubleValue());
    }

    @Override
    public void write(JSONWriter jsonWriter, Object object, Object fieldName, Type fieldType, long features) {
        if (object == null) {
            jsonWriter.writeNull();
            return;
        }

        if (format != null) {
            String str = format.format(object);
            jsonWriter.writeRaw(str);
            return;
        }

        jsonWriter.writeDouble(((Double) object).doubleValue());
        if (((jsonWriter.getFeatures() | features) & JSONWriter.Feature.WriteClassName.mask) != 0
                && fieldType != Double.class && fieldType != double.class) {
            jsonWriter.writeRaw('D');
        }
    }
}
