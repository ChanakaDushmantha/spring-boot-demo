up_all:
	docker-compose up --build -d

up_demo:
	docker-compose up --build -d demo_management

restart_demo:
	docker-compose stop demo_management
	docker-compose rm -f demo_management
	docker-compose up -d demo_management

down_demo:
	docker-compose stop demo_management
	docker-compose rm -f demo_management
	docker tag demo_management:v1.0.0 demo_management:bkup_$(shell date +%Y%m%d%H%M%S)
	docker image rm demo_management:v1.0.0
