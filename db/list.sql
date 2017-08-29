BEGIN TRANSACTION;
CREATE TABLE "product" (
	`idProduct`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`nameProduct`	TEXT NOT NULL,
	`priceProduct`	REAL DEFAULT 0,
	`idList_Product`	INTEGER NOT NULL,
	`quantProduct`	INTEGER DEFAULT 1,
	FOREIGN KEY(`idList_Product`) REFERENCES `list`(`idList`) 
);
CREATE TABLE "list" (
	`idList`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`nameList`	INTEGER
);
COMMIT;
