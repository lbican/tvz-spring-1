package hr.tvz.lbican.studapp.security.error;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;

class LowerCaseClassNameResolver extends TypeIdResolverBase {

    @Override
    public String idFromValue(Object value) {
        return value.getClass().getSimpleName().toLowerCase();
    }

    @Override
    public String idFromValueAndType(Object value, Class<?> suggestedType) {
        return idFromValue(value);
    }

    @Override
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.CUSTOM;
    }
}
