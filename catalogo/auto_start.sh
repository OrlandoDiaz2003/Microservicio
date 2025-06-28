sudo apt update & sudo apt upgrade

sudo apt install docker.io docker-compose

PROJECT_DIR="/home/ubuntu/Microservicio/catalogo"

 # Colores para los mensajes
 GREEN='\033[0;32m'
 YELLOW='\033[1;33m'
 NC='\033[0m' 

 echo -e "${YELLOW} ELIMINANDO MAQUINAS DOCKER ANTERIORES"
 echo ""

docker-compose down

echo -e "${GREEN} MAQUINA DETINADA"
echo ""


echo -e "${YELLO} LEVANTANDO BASE DE DATOS"
echo ""

docker-compose -f "docker-compose.yml" up --build -d "mysql-db"

if [ $? -ne 0 ]; then
    echo -e "\033[0;31mError: Fallo al levantar la base de datos. Revisa los logs con 'docker compose logs'${NC}"
    exit 1
fi


echo -e "${GREEN} BASE DE DATOS LEVANTADA"
echo ""

sleep 3

echo -e "${YELLOW} LEVANTANDO CATALOGO APP"
echo ""

docker-compose -f "docker-compose.yml" up --build -d "catalog-app"

if [ $? -ne 0 ]; then
    echo -e "\033[0;31mError: Fallo al levantar catalogo app. Revisa los logs con 'docker compose logs'${NC}"
    exit 1
fi


