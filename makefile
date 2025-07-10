stop-dockers:
	@docker stop $(shell docker ps -a -q)

build-dockers:
	@docker build -t gamesprer-scraper:latest -f ./scraper/Dockerfile ./scraper | docker build -t gamesprer-back:latest -f ./backend/Dockerfile ./backend | docker build -t gamesprer-front:latest -f ./frontend/Dockerfile ./frontend


.PHONY: stop-dockers, build-dockers


