# Session 11 - Bai 2: Redis cache thuoc

Chay `docker compose up -d --wait`, sau do `./gradlew :pharmacy-service:bootRun`.

- GET `http://localhost:8090/api/medicines/1`: lan dau co SQL, lan sau doc Redis.
- PUT `http://localhost:8090/api/medicines` voi JSON `{{"id":1,"name":"Panadol","price":45000}}`: xoa key `medicines::1`; TTL 60 phut.
- Xem Redis: `docker compose exec redis redis-cli --raw KEYS 'medicines::*'`.
- Du lieu mau co ID 1. Cac lan do thoi gian phu thuoc may, khong cam ket moc 10ms.
