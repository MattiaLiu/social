package com.cnnp.social.collspace.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cnnp.social.collspace.manager.CollspaceManager;
import com.cnnp.social.collspace.manager.dto.CollspaceDto;
import com.cnnp.social.collspace.manager.dto.CollspaceTopicDto;
import com.cnnp.social.collspace.manager.dto.CollspaceUserDto;
import com.cnnp.social.collspace.repository.entity.TCollspaceTopic;



@RestController
@RequestMapping("/api/V1.0/collspace")
public class CollspaceController {
	@Autowired
	private CollspaceManager collspaceManger;
    //鎴戠鐞嗙殑
	@RequestMapping(value = "/MyColl/{userid}", method = RequestMethod.GET)
	public @ResponseBody List<CollspaceDto> viewMycoll(@PathVariable("userid") String userid) {
		return collspaceManger.findMyColl(userid);
	}
	//鎴戝姞鍏ョ殑
	@RequestMapping(value = "/Coll/{userid}", method = RequestMethod.GET)
	public @ResponseBody List<CollspaceDto> view(@PathVariable("userid") String userid) {
		return collspaceManger.findByFilter(userid);
	}
	//鍏叡
	@RequestMapping(value = "/Collopen/{userid}", method = RequestMethod.GET)
	public @ResponseBody List<CollspaceDto> viewOpen(@PathVariable("userid") String userid) {
		return collspaceManger.findOpenColl(userid);
	}
	//鎴愬憳
	@RequestMapping(value = "/Collmember/{collspaceid}", method = RequestMethod.GET)
	public @ResponseBody List<CollspaceUserDto> viewmember(@PathVariable("collspaceid") String collspaceid) {
		return collspaceManger.findmember(collspaceid,"1");
	}
	//绠＄悊鍛�	
	@RequestMapping(value = "/Colladmin/{collspaceid}", method = RequestMethod.GET)
	public @ResponseBody List<CollspaceUserDto> viewadmin(@PathVariable("collspaceid") String collspaceid) {
		return collspaceManger.findmember(collspaceid,"2");
	}

	//鍒涘缓sdf
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void save(@RequestBody CollspaceDto collspace) {
		collspaceManger.savecoll(collspace); 
	}
	
	//查询发帖信息 wzy
	@RequestMapping(value = "/Colltopic/{topicid}",method = RequestMethod.GET)
	@ResponseBody
	public List<CollspaceTopicDto> viewtopic(@PathVariable("topicid")String topicid){
		return collspaceManger.findtopiclist(topicid);
	}
	
	//保存发帖信息wzy
	@RequestMapping(value ="/Colltopic/add",method = RequestMethod.POST)
	@ResponseBody
	public CollspaceTopicDto savetopic(@RequestBody TCollspaceTopic tCollspaceTopic){
		final Calendar today=Calendar.getInstance();
		tCollspaceTopic.setCreatetime(today.getTime());
		tCollspaceTopic.setUpdatetime(today.getTime());
		return collspaceManger.savetopic(tCollspaceTopic);
	}
}