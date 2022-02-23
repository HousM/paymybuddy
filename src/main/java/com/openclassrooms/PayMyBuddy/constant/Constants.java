//package com.openclassrooms.PayMyBuddy.constant;
//
//public class Constants {
//	public static String SaveUser = "insert into user(email, password, first_name, last_name, phone) values(?,?,?,?,?)";
//	public static String GetUserByEmail = "select id, email, first_name, last_name from user where email=?";
//	public static String GetUserById = "select email, name from user where id=?";
//
//	public static String Savebuddy_account = "insert into buddy_account(user_id, balance) values (?,?)";
//	public static String Getbuddy_accountByBalance = "select id, balance, user_id from buddy_account where balance=?";
//	public static String Getbuddy_accountByUser_id = "select id, balance, user_id from buddy_account where user_id=?";
//	public static String Updatebuddy_account = "update buddy_account set balance=? where id=?";
//
//	public static String SaveBank_account = "insert into bank_account(user_id,bic,iban) values (?,?,?)";
//	public static String GetBank_account = "select * from bank_account where id=?";
//	public static String GetBank_accountIban = "select * from bank_account where iban=?";
//
//	public static String SaveTransaction = "insert into transaction(buddy_account_receiver_id,buddy_account_owner_id,bank_account_id,description,amount,fee,date,type) values(?,?,?,?,?,?,?,?)";
//	public static String GetTransactions = "select id,description,amount,fee,date,type from transaction where id=? order by id desc limit 10";
//}
