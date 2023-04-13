package com.test.port;

import com.test.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BrandMapper {
      List<Brand> selectAll();//查询所有
      Brand selectById(int id);//根据id查询

      /*List <Brand>selectByCondition(@Param("status")int status,
                                    @Param("companyName") String companyName,
                                    @Param("brandName") String brandName);
       */
            //@Param参数与sql语句对应(散装参数)

      /*List <Brand>selectByCondition(Brand brand);
                  传封装对象*/
      List <Brand>selectByCondition(Map map); //动态查询某些值
      List <Brand>selectByConditionSingle(Map map);     //单条件动态查询

      void add(Brand brand); //增加
      int update(Brand brand); //修改

      int deleteById(int id); //删除
      void deleteByIds(@Param("ids") int []ids);
}
