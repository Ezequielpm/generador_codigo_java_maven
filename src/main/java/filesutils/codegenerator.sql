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
    id SERIAL PRIMARY KEY,
    ip VARCHAR(25),
    port VARCHAR(15),
    database_name VARCHAR(40),
    user_name VARCHAR(40),
    password_db VARCHAR(50)    
);






      Name       |  Owner   | Encoding | Locale Provider | Collate | Ctype | ICU Locale | ICU Rules |   Access privileges   
-----------------+----------+----------+-----------------+---------+-------+------------+-----------+-----------------------
 aseguradora     | postgres | UTF8     | libc            | C       | C     |            |           | 
 bundesliga      | postgres | UTF8     | libc            | C       | C     |            |           | 
 codegenerator   | postgres | UTF8     | libc            | C       | C     |            |           | 
 control_escolar | postgres | UTF8     | libc            | C       | C     |            |           | 
 convertidor     | postgres | UTF8     | libc            | C       | C     |            |           | 
 java3g          | postgres | UTF8     | libc            | C       | C     |            |           | 
 ligafutbol      | postgres | UTF8     | libc            | C       | C     |            |           | 
 ligafutbol2     | postgres | UTF8     | libc            | C       | C     |            |           | 
 loteria         | postgres | UTF8     | libc            | C       | C     |            |           | 
 postgres        | postgres | UTF8     | libc            | C       | C     |            |           | 
 queso           | postgres | UTF8     | libc            | C       | C     |            |           | 
 queso2          | postgres | UTF8     | libc            | C       | C     |            |           | 
 template0       | postgres | UTF8     | libc            | C       | C     |            |           | 

