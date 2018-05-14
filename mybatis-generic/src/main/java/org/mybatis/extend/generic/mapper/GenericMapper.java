package org.mybatis.extend.generic.mapper;

import org.mybatis.extend.generic.mapper.base.*;
import org.mybatis.extend.generic.model.BaseModel;

import java.io.Serializable;

/**
 * GenericMapper
 *
 * Created by Bob Jiang on 2017/2/10.
 */
public interface GenericMapper<T extends BaseModel<PK>, PK extends Serializable>
        extends InsertMapper<T>, DeleteMapper<PK>, UpdateMapper<T>, SelectMapper<T>, SelectPrimaryKeyMapper<T, PK> {

}
