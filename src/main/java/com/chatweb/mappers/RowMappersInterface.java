/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.chatweb.mappers;

import java.sql.ResultSet;

/**
 *
 * @author ACER
 */
public interface RowMappersInterface<T> {
    T mapRow(ResultSet rs);
}
