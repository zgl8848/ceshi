package com.campus.grid.api.dto;

import java.util.List;

/**
 * 泛型的定义
 * 方法级别  修饰符 跟 返回值之间写T
 * 类级别定义 在类名后 加上<T>
 */
public class PageBean<T> {
	/**
	 * 6个参数
	 * 2个传入的
	 * pageNumber 当前页
	 * pageSize 每页显示个数
	 * 2个查的
	 * totalRecord 总条数
	 * data 查询出分页的数据
	 * 2个算的
	 * startIndex 数据库查询的开始索引
	 * totalPage 页面总页数
	 */
	// 当前页
	private int pageNumber;
	//每页显示个数
	private int pageSize;
	//总条数
	private int totalRecord;
	//数据库查询的开始索引
	private int startIndex;
	//页面总页数
	private int totalPage;
	//查询出分页的数据
	private List<T> data;

	/**
	 * 告警分页需要的数据: current size total pages records
	 */
	private Integer current;//当前页码
	private Integer size;//每页显示多少个数据
	private Integer total;//总条数
	private Integer pages;//总页数
	private List<T> records;//查询出分页后的数据

/*
	//添加start 和 end 动态条 前五后四

	private int start ;
	private int end ;

	 // 计算动态分页条

	private void caculate(){
		
		//总页数 小于10页
		if(getTotalPage()<10){
			//此处循环不够10页
			start=1;
			end = getTotalPage();
		}else{
			//总页数大于10页
			start=pageNumber-5;
			end=pageNumber+4;
			//如果本来在第一页 前五条
			if( start < 1 ){
				start=1;
				end=10;
			}
			//如果在最后一页 end > getTotalPage()
			if(end > getTotalPage()){
				start = getTotalPage() - 9;
				end=getTotalPage();
			}
		}
	}

	public int getStart() {
		caculate();
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		caculate();
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
 */

	public PageBean(int pageNumber, int pageSize) {
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	/**
	 * 告警分页需要的数据 setter() getter()
	 */
	public PageBean() {
	}

	public int getStartIndex() {
		//计算
		startIndex = (pageNumber - 1) * pageSize;
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getTotalPage() {
		//计算总页数
		double ceil = Math.ceil((totalRecord * 1.0) / pageSize);
		return (int) ceil;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Integer getCurrent() {
		return current;
	}

	public void setCurrent(Integer current) {
		this.current = current;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}
}
