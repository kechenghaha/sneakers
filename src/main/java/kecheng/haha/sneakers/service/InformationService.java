package kecheng.haha.sneakers.service;

import kecheng.haha.sneakers.dto.InformationDTO;
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

    public List<InformationDTO> list() {
        List<Information> informations = informationMapper.list();
        List<InformationDTO> informationDTOList = new ArrayList<>();
        for (Information information : informations) {
            User user = userMapper.findById(information.getCreator());
            InformationDTO informationDTO = new InformationDTO();
            BeanUtils.copyProperties(information, informationDTO);
            informationDTO.setUser(user);
            informationDTOList.add(informationDTO);
        }
        return informationDTOList;
    }
}
