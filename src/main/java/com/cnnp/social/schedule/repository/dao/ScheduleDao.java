package com.cnnp.social.schedule.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.cnnp.social.schedule.repository.entity.TSchedule;


@Transactional
public interface ScheduleDao extends CrudRepository<TSchedule, Long> ,JpaSpecificationExecutor<TSchedule>{
	@Query("select schedule from TSchedule schedule where schedule.id = ?1")
	public TSchedule findOne(Long id);
	//public List<TSupervision> search()
	@Query("select schedule from TSchedule schedule where schedule.userid = ?1")
	public List<TSchedule> findall(String userid);
	@Query("select schedule from TSchedule schedule where schedule.id = ?1 and schedule.startdate> TO_DATE(?2, 'YYYY-MM-DD HH24:MI:SS') and schedule.startdate <  TO_DATE(?3, 'YYYY-MM-DD HH24:MI:SS') ")
	public TSchedule findate(Long id,String startdate,String enddate);
}
