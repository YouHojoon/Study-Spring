SELECT @rownum:=@rownum+1 AS rowNum,`board`.* FROM `board`/*!USE INDEX(PRIMARY)*/,
			(SELECT @rownum:=0)R ORDER BY update_date DESC limit 10,10