stop-dockers:
	@docker stop $(shell docker ps -a -q)

build-dockers:
	@docker build -t gamesprer-scraper:latest -f ./scraper/Dockerfile ./scraper | docker build -t gramesprer-back:latest -f ./backend/Dockerfile ./backend


.PHONY: stop-dockers, build-dockers


