package com.qianxunclub.permanent.service;

import com.qianxunclub.permanent.model.PostSubjectCategories;
import com.qianxunclub.permanent.model.SubjectCategoriesNode;
import com.qianxunclub.permanent.repository.dao.SubjectCategoriesDao;
import com.qianxunclub.permanent.repository.entity.SubjectCategoriesEntity;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

/**
 * @author zhangbin
 */
@Service
@AllArgsConstructor
public class SubjectCategoriesService {

    private final SubjectCategoriesDao subjectCategoriesDao;

    public List<SubjectCategoriesNode> tree(Long customersId) {
        List<SubjectCategoriesNode> tree = new ArrayList<>();
        List<SubjectCategoriesEntity> rootNodes = subjectCategoriesDao.getRootNodes(customersId);
        rootNodes.forEach(node -> {
            List<SubjectCategoriesEntity> childNodes = subjectCategoriesDao
                .getChildNodes(customersId, node.getId());

            SubjectCategoriesNode subjectCategoriesNode = new SubjectCategoriesNode();
            BeanUtils.copyProperties(node, subjectCategoriesNode);
            subjectCategoriesNode.setChileNodes(childNodes);
            tree.add(subjectCategoriesNode);
        });
        return tree;
    }

    @Transactional(rollbackFor = {Exception.class})
    public void insertAndRefreshOrderNumber(Long customersId,
        PostSubjectCategories postSubjectCategories) {
        Long parentId = null;
        if (!ObjectUtils.isEmpty(postSubjectCategories.getParentId())) {
            SubjectCategoriesEntity parentNode = subjectCategoriesDao
                .get(customersId, postSubjectCategories.getParentId());
            if (parentNode != null) {
                parentId = parentNode.getId();
            }
        }

        SubjectCategoriesEntity subjectCategoriesEntity = new SubjectCategoriesEntity();
        subjectCategoriesEntity.setCustomersId(customersId);
        subjectCategoriesEntity.setCategoriesName(postSubjectCategories.getCategoriesName());
        subjectCategoriesEntity.setParentId(parentId);
        Long orderNumber;
        if (!ObjectUtils.isEmpty(postSubjectCategories.getOrderNumber())) {
            orderNumber = postSubjectCategories.getOrderNumber();
            if (subjectCategoriesDao
                .isExistOrderNumber(customersId, postSubjectCategories.getOrderNumber())) {
                subjectCategoriesDao.refreshOrderNumber(customersId, parentId,
                    postSubjectCategories.getOrderNumber());
            }
        } else {
            orderNumber = subjectCategoriesDao.maxOrderNumber(customersId, parentId);
        }
        subjectCategoriesEntity.setOrderNumber(orderNumber);
        subjectCategoriesDao.addNode(subjectCategoriesEntity);
    }

}
