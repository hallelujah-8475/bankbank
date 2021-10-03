package main

import (
	"database/sql"
	"fmt"
	_ "github.com/lib/pq"
	"log"
)

const (
	HOST     = "XXX"
	DATABASE = "XXX"
	USER     = "XXX"
	PASSWORD = "XXX"
)

type EMPLOYEE struct {
	id       string
	loginid  string
	password string
}

func main() {

	var connectionString string = fmt.Sprintf("host=%s user=%s password=%s dbname=%s sslmode=disable", HOST, USER, PASSWORD, DATABASE)

	db, err := sql.Open("postgres", connectionString)
	if err != nil {
		log.Fatalln("接続失敗", err)
	}
	defer db.Close()

	// SELECT
	rows, err := db.Query("SELECT id, loginid, password FROM systemuser")

	if err != nil {
		fmt.Println(err)
	}

	var es []EMPLOYEE
	for rows.Next() {
		var e EMPLOYEE
		rows.Scan(&e.id, &e.loginid, &e.password)
		es = append(es, e)
	}

	fmt.Println(es)
}
