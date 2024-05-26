/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.chatweb.daos;

import com.chatweb.mappers.RowMappersInterface;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface GenericDaoInterface<T> {

    List<T> query(String sql, RowMappersInterface<T> rowMapper, Object... parameters);

    Long save(String sql, Object... parameters);

    int count(String sql, Object... parameters);
}
