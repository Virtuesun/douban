package com.douban.controller;

import com.douban.entity.po.FilmCritics;
import com.douban.entity.vo.Page;
import com.douban.service.filmcritics.FilmCriticsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lhw2 on 2017/5/1.
 */
@Controller
public class FilmCriticsController {

    @Resource
    private FilmCriticsService filmCriticsService;


    /**
     * 添加影评
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "film_critics", method = RequestMethod.POST)
    public Object insertFilmCritics(@RequestBody FilmCritics filmCritics, HttpSession session) {
        int success = filmCriticsService.insertFilmCritics(filmCritics,session);
        Map<String,Object> map = new HashMap<>();
        if (success > 0) {
            map.put("操作状态","成功");
        } else {
            map.put("操作状态","失败");
        }
        return map;
    }

    /**
     *
     * @param filmCriticsId 影评Id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "film_critics/{film_critics_id}", method = RequestMethod.DELETE)
    public Object deleteFilmCritics(@PathVariable("film_critics_id") Long filmCriticsId) {
        int success = filmCriticsService.deleteFilmCriticsById(filmCriticsId);
        Map<String,Object> map = new HashMap<>();
        if (success > 0) {
            map.put("操作状态","成功");
        } else {
            map.put("操作状态","失败");
        }
        return map;
    }

    /**
     * 获得本电影本页影评
     * @param filmId 电影Id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "film_critics/{film_id}", method = RequestMethod.POST)
    public Object getFilmCriticsList(@PathVariable("film_id") Long filmId,@RequestBody Map<String,Object> pageMap) {
        Page page = new Page();
        page.setCurrPageNo(Long.valueOf(pageMap.get("currPageNo").toString()));
        page.setPageSize(Long.valueOf(pageMap.get("pageSize").toString()));
        Map<String,Object> map = filmCriticsService.getFilmCriticsList(filmId,page);
        return map;
    }

    /**
     * 获得本影评详情
     * @param filmCriticsId 影评Id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "film_critics/detail/{film_critics_id}", method = RequestMethod.GET)
    public Object getFilmCriticsDetail(@PathVariable("film_critics_id") Long filmCriticsId) {
        FilmCritics filmCritics = filmCriticsService.getFilmCriticsById(filmCriticsId);
        Map<String,Object> map = new HashMap<>();
        map.put("影评详情",filmCritics);
        return map;
    }

}
