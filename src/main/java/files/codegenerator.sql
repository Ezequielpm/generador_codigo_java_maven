/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  mariormoreno & ezequiel
 * Created: Mar 6, 2025
 */

-- Cami2010/07
CREATE DATABASE codegenerator;
\c codegenerator

CREATE TABLE databases
(
    id INT SERIAL PRIMARY KEY,
    ip VARCHAR(25),
    port VARCHAR(10),
    database_name VARCHAR(40),
    user_name VARCHAR(40),
    password VARCHAR(50)    
);

