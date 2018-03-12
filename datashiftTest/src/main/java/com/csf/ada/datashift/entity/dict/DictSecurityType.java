package com.csf.ada.datashift.entity.dict;

import org.bson.types.ObjectId;

import com.csf.app.dal.entity.AbbrMultiLanguage;
import com.csf.platform.dataentry.entity.dict.DictBase;
import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@Entity(value = "dict_security_type", noClassnameStored = true)
public class DictSecurityType extends DictBase {
    @Id
    private ObjectId id;

    @Embedded
    private AbbrMultiLanguage category;

    public AbbrMultiLanguage getCategory() {
        return category;
    }

    public void setCategory(AbbrMultiLanguage category) {
        this.category = category;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
