package org.mybatis.extend.generic.service;

import org.mybatis.extend.generic.mapper.GenericMapper;
import org.mybatis.extend.generic.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * GenericService
 *
 * Created by Bob Jiang on 2017/2/13.
 */
public interface GenericService<T extends BaseModel<PK>, PK extends Serializable> extends GenericMapper<T, PK> {

}
