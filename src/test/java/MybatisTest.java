import com.test.pojo.Brand;
import com.test.port.BrandMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisTest {

    @Test
    public void testSelectAll() throws IOException {
        // 加载mybatis的核心配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //获取mapper接口代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        //执行方法
        List<Brand> brands = brandMapper.selectAll();
        System.out.println(brands);

        sqlSession.close();
    }

    @Test
    public void testSelectById() throws IOException {
        int id=2;
        // 加载mybatis的核心配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //获取mapper接口代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        //执行方法
        Brand brand = brandMapper.selectById(id);
        System.out.println(brand);

        sqlSession.close();
    }

    @Test
    public void testSelectByCondition() throws IOException {
        //接收参数
        int status=1;
        String companyName="华为";
        String brandName="华为";

        //处理参数
        companyName ="%"+companyName+"%";
        brandName ="%"+brandName+"%";

        //封装对象
        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setCompany_name(companyName);
        brand.setBrand_name(brandName);

        Map map=new HashMap<>();
        map.put("status",status);
        map.put("companyName",companyName);
        map.put("brandName",brandName);

        // 加载mybatis的核心配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //获取mapper接口代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        //执行方法
        //1 List<Brand> brands = brandMapper.selectByCondition(status, companyName, brandName);
        //2 List<Brand> brands = brandMapper.selectByCondition(brand);
        List<Brand> brands = brandMapper.selectByCondition(map);
        System.out.println(brands);

        sqlSession.close();
    }

    @Test
    public void testSelectByConditionSingle() throws IOException {
        //接收参数
        int status=1;
        String companyName="华";
        String brandName="华";

        //处理参数
        companyName ="%"+companyName+"%";
        brandName ="%"+brandName+"%";

        Map map=new HashMap<>();
        //map.put("status",status);
        map.put("companyName",companyName);
        //map.put("brandName",brandName);

        // 加载mybatis的核心配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //获取mapper接口代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        //执行方法
        //1 List<Brand> brands = brandMapper.selectByCondition(status, companyName, brandName);
        //2 List<Brand> brands = brandMapper.selectByCondition(brand);
        List<Brand> brands = brandMapper.selectByConditionSingle(map);
        System.out.println(brands);

        sqlSession.close();
    }

    @Test
    public void testAdd() throws IOException {
        //接收参数
        int status=1;
        String companyName="APPLE公司";
        String brandName="APPLE";
        String description="苹果重新定义了手机";
        int ordered = 100;

        //处理参数
        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setBrand_name(brandName);
        brand.setCompany_name(companyName);
        brand.setDescription(description);
        brand.setOrdered(ordered);

        // 加载mybatis的核心配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //获取mapper接口代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        //执行方法
        brandMapper.add(brand);
        Integer id=brand.getId();

        //提交事务
        //sqlSession.commit();
        System.out.println(id);

        sqlSession.close();
    }

    @Test
    public void testUpdate() throws IOException {
        //接收参数
        int status=0;
        String companyName="APPLE公司";
        String brandName="APPLE";
        String description="苹果重新定义了手机";
        int ordered = 250;
        int id=11;

        //处理参数
        Brand brand = new Brand();
        brand.setBrand_name(brandName);
        brand.setCompany_name(companyName);
        brand.setOrdered(ordered);
        brand.setDescription(description);
        brand.setStatus(status);
        brand.setId(id);

        // 加载mybatis的核心配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //获取mapper接口代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        //执行方法
        int count = brandMapper.update(brand);


        //提交事务
        //sqlSession.commit();
        System.out.println(count);

        sqlSession.close();
    }

    @Test
    public void testDeleteById() throws IOException {
        int id=10;
        // 加载mybatis的核心配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //获取mapper接口代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        //执行方法
        int count = brandMapper.deleteById(id);
        System.out.println(count);

        sqlSession.close();
    }

    @Test
    public void testDeleteByIds() throws IOException {
        int ids[]={8};
        // 加载mybatis的核心配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //获取mapper接口代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        //执行方法
            brandMapper.deleteByIds(ids);
        sqlSession.close();
    }

}

