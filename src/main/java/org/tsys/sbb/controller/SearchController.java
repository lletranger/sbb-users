package org.tsys.sbb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tsys.sbb.dto.BoardDto;
import org.tsys.sbb.model.*;
import org.tsys.sbb.service.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class SearchController {

    private BoardService boardService;
    private StationService stationService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    @Autowired
    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    @RequestMapping(value = "/search")
    public String search(Model model) {

        model.addAttribute("stations", stationService.getAllStations());
        LOGGER.info("Creating new search form");
        return "search";
    }

    @RequestMapping("/searchboards")
    public String searchOpen(@RequestParam("id1") int id1, @RequestParam("id2") int id2,
                             @RequestParam("time1") String time1, @RequestParam("time2") String time2,
                             Model model) {

        model.addAttribute("s1", id1);
        model.addAttribute("s2", id2);
        model.addAttribute("time1", time1);
        model.addAttribute("time2", time2);

        List<BoardDto> dtos = boardService.findBoards(id1, id2, time1, time2)
                .stream()
                .map(board -> boardService.getSearchDto(board))
                .collect(Collectors.toList());

        model.addAttribute("board", new Board());
        model.addAttribute("stations", stationService.getAllStations());
        model.addAttribute("dtos", dtos);

        LOGGER.info("Getting results of the search request");
        return "search";
    }
}