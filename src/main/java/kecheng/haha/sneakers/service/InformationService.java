package kecheng.haha.sneakers.service;

import kecheng.haha.sneakers.dto.InformationDTO;
import kecheng.haha.sneakers.dto.PaginationDTO;
import kecheng.haha.sneakers.mapper.InformationMapper;
import kecheng.haha.sneakers.mapper.UserMapper;
import kecheng.haha.sneakers.model.Information;
import kecheng.haha.sneakers.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * by kecheng
 * 2020/06/09
 */
@Service
public class InformationService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private InformationMapper informationMapper;

    public PaginationDTO list(Integer page, Integer size) {
        Integer totalPage;
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = informationMapper.count();
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage, page);

        Integer offset = size * (page - 1);
        List<Information> informationList = informationMapper.list(offset, size);
        List<InformationDTO> informationDTOList = new ArrayList<>();
        for (Information information : informationList) {
            User user = userMapper.findById(information.getCreator());
            InformationDTO informationDTO = new InformationDTO();
            BeanUtils.copyProperties(information, informationDTO);
            informationDTO.setUser(user);
            informationDTOList.add(informationDTO);
        }
        paginationDTO.setInformationDTOList(informationDTOList);

        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        Integer totalPage;
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = informationMapper.countByUserId(userId);
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage, page);

        Integer offset = size * (page - 1);
        List<Information> informationList = informationMapper.listByUserId(userId, offset, size);
        List<InformationDTO> informationDTOList = new ArrayList<>();
        for (Information information : informationList) {
            User user = userMapper.findById(information.getCreator());
            InformationDTO informationDTO = new InformationDTO();
            BeanUtils.copyProperties(information, informationDTO);
            informationDTO.setUser(user);
            informationDTOList.add(informationDTO);
        }
        paginationDTO.setInformationDTOList(informationDTOList);

        return paginationDTO;
    }
}
