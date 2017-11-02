package org.mybatis.extend.generic.mapper.base;

import java.io.Serializable;

/**
 * Created by Bob Jiang on 2017/11/2.
 */
public interface DeleteMapper<PK extends Serializable> {

    /**
     * 按主键删除数据，物理删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(PK id);

}
