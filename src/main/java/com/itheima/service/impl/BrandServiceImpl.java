package com.itheima.service.impl;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.testng.annotations.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class BrandServiceImpl implements BrandService {
    //1.创建SqlSessionFactory工厂对象
    SqlSessionFactory factory=SqlSessionFactoryUtils.getSqlSessionFactory();


    @Override
    public List<Brand> selectAll() {
        //2.获取SqlSession对象
        SqlSession sqlSession=factory.openSession();
        //3.获取BrandMapper
        BrandMapper mapper=sqlSession.getMapper(BrandMapper.class);
        //4.调方法
        List<Brand> brands=mapper.SelectAll();
        //5.释放资源
        sqlSession.close();
        return brands;
    }

    @Override
    public void add(Brand brand) {
        //2.获取SqlSession对象
        SqlSession sqlSession=factory.openSession();
        //3.获取BrandMapper
        BrandMapper mapper=sqlSession.getMapper(BrandMapper.class);
        //4.调方法
        mapper.add(brand);
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();

    }

    @Override
    public void delectById(int[] ids) {
        //2.获取SqlSession对象

        SqlSession sqlSession=factory.openSession();
        //3.获取BrandMapper
        BrandMapper mapper=sqlSession.getMapper(BrandMapper.class);
        //4.调方法
        mapper.delectById(ids);
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();

    }

    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        //2.获取SqlSession对象
        SqlSession sqlSession=factory.openSession();
        //3.获取BrandMapper
        BrandMapper mapper=sqlSession.getMapper(BrandMapper.class);
        //4.计算开始索引
        int begin=(currentPage-1)*pageSize;
        int size =pageSize;
        //查询当前页数据
        List<Brand> rows=mapper.selectByPage(begin,size);
        //6.查询总记录数
        int totalCount=mapper.selectTotalCount();

        //7封装pageBean对象
        PageBean<Brand> pageBean=new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        //8.释放资源
        sqlSession.close();
        return pageBean;
    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        //2.获取SqlSession对象
        SqlSession sqlSession=factory.openSession();
        //3.获取BrandMapper
        BrandMapper mapper=sqlSession.getMapper(BrandMapper.class);
        //4.计算开始索引
        int begin=(currentPage-1)*pageSize;
        int size =pageSize;
        //处理brand,由于是模糊查询

        String brandName=brand.getBrandName();
        if(brandName!=null && brandName.length()>0){
            brand.setBrandName("%"+brandName+"%");
        }

        String companyName=brand.getCompanyName();
        if(companyName!=null && companyName.length()>0){
            brand.setCompanyName("%"+companyName+"%");
        }
        System.out.println(brand.getCompanyName());
        //查询当前页数据
        List<Brand> rows=mapper.selectByPageAndCondition(begin,size,brand);
        //6.查询总记录数
        int totalCount=mapper.selectTotalCountByCondition(brand);

        //7封装pageBean对象
        PageBean<Brand> pageBean=new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        //8.释放资源
        sqlSession.close();
        return pageBean;
    }
   /* @Test
    void s() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<Brand> b= Brand.class;
        Brand brand=new Brand();
        Method m= b.getMethod("setCompanyName",String.class);
        m.invoke(brand,"%"+"小米"+"%");
        System.out.println(brand.getCompanyName());
    }*/

}
