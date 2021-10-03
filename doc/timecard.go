package main

import (
	"database/sql"
	"fmt"
	_ "github.com/lib/pq"
	"log"
	"time"
)

const (
	// Initialize connection constants.
	HOST     = "XXX"
	DATABASE = "XXX"
	USER     = "XXX"
	PASSWORD = "XXX"
)

type KoinMaster struct {
	id int
}

func main() {

	// DB接続
	var connectionString string = fmt.Sprintf("host=%s user=%s password=%s dbname=%s sslmode=disable", HOST, USER, PASSWORD, DATABASE)
	db, err := sql.Open("postgres", connectionString)
	if err != nil {
		log.Fatalln("接続失敗", err)
	}
	defer db.Close()

	// 日付の取得
	formatDate := time.Now().Format("2006/01/02")

	// SELECT
	rows, err := db.Query("SELECT id FROM koinmaster")

	// エラー処理
	if err != nil {
		fmt.Println(err)
	}

	for rows.Next() {

		var koinMaster KoinMaster
		rows.Scan(&koinMaster.id)

		// INSERT
		sqlStatement := `INSERT INTO timecard(koinid,workdate) VALUES ($1,$2)`
		_, err = db.Exec(sqlStatement, koinMaster.id, formatDate)
		if err != nil {
			panic(err)
		}
	}
}
