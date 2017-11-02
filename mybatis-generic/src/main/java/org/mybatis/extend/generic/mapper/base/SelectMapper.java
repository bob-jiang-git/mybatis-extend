package org.mybatis.extend.generic.mapper.base;

import org.mybatis.extend.generic.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Bob Jiang on 2017/11/2.
 */
public interface SelectMapper<T extends BaseModel<PK>, PK extends Serializable> {

    /**
     * 按ID查询
     * @param id
     * @return
     */
    T selectByPrimaryKey(PK id);

    /**
     * 查询所有数据
     *
     * @return
     */
    List<T> selectAll();

}
