Приложение для хранения плейлистов пользователей

Функции:

1. Сохранить email   

POST /playlist/email  
Параметры: email

2. Добавить песню  

POST /playlist/addSong  
Параметры: title, artist  
(требуется cookie JSESSIONID)

3. Удалить песню  

POST /playlist/removeSong  
Параметры: title  
(требуется cookie JSESSIONID)

4. Посмотреть плейлист  

GET /playlist/playlist  
(требуется cookie JSESSIONID)
