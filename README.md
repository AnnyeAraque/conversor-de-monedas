# Conversor de Divisas

Una aplicación Java que permite convertir entre diferentes divisas utilizando tasas de cambio en tiempo real obtenidas de la API FastForex.

## Características

- ✅ Mensaje de bienvenida atractivo
- ✅ Menú de acciones con opción de salida
- ✅ Captura de valores por teclado
- ✅ Captura de tipos de moneda
- ✅ Conexión a API en tiempo real
- ✅ Procesamiento de datos JSON con Gson
- ✅ Interfaz de usuario amigable con caracteres Unicode
- ✅ Validación de entrada de datos
- ✅ Manejo de errores robusto

## Requisitos

- Java 8 o superior
- Biblioteca Gson (incluida en el proyecto)
- Conexión a Internet para acceder a la API

## Estructura del Proyecto

```
conversor-de-monedas/
├── src/
│   ├── Principal.java          # Clase principal que ejecuta la aplicación
│   └── CurrencyConverter.java  # Clase principal del conversor
├── lib/
│   └── gson-2.13.1.jar        # Biblioteca para procesamiento JSON
└── README.md                   # Este archivo
```

## Cómo Ejecutar

### Opción 1: Desde el IDE
1. Abre el proyecto en tu IDE preferido (IntelliJ IDEA, Eclipse, NetBeans)
2. Asegúrate de que la biblioteca Gson esté en el classpath
3. Ejecuta la clase `Principal` o `CurrencyConverter`

### Opción 2: Desde la línea de comandos
```bash
# Compilar
javac -cp "lib/*" src/*.java

# Ejecutar
java -cp "lib/*;src" Principal
```

## Funcionalidades

### 1. Realizar Conversión de Divisas
- Selecciona la moneda origen
- Selecciona la moneda destino
- Ingresa la cantidad a convertir
- Obtiene el resultado en tiempo real

### 2. Ver Monedas Disponibles
- Muestra todas las divisas disponibles en la API
- Incluye el total de monedas soportadas

### 3. Salir
- Cierra la aplicación de manera elegante

## API Utilizada

La aplicación utiliza la API de FastForex para obtener las tasas de cambio en tiempo real:
- **URL**: `https://api.fastforex.io/fetch-all?41898787ef-375d349d19-t0qdnm`
- **Formato**: JSON
- **Actualización**: Tiempo real

## Ejemplo de Uso

```
╔══════════════════════════════════════════════════════════════╗
║                    CONVERSOR DE DIVISAS                    ║
║                                                              ║
║  ¡Bienvenido al conversor de divisas en tiempo real!       ║
║  Obtenga las tasas de cambio más actualizadas del mercado  ║
╚══════════════════════════════════════════════════════════════╝

╔══════════════════════════════════════════════════════════════╗
║                        MENÚ PRINCIPAL                      ║
╠══════════════════════════════════════════════════════════════╣
║  1. Realizar conversión de divisas                         ║
║  2. Ver monedas disponibles                                ║
║  3. Salir                                                  ║
╚══════════════════════════════════════════════════════════════╝
Seleccione una opción: 1

╔══════════════════════════════════════════════════════════════╗
║                    CONVERSIÓN DE DIVISAS                    ║
╚══════════════════════════════════════════════════════════════╝
Ingrese el código de la moneda origen (ej: USD, EUR, MXN): USD
Ingrese el código de la moneda destino (ej: USD, EUR, MXN): EUR
Ingrese la cantidad a convertir: 100

╔══════════════════════════════════════════════════════════════╗
║                        RESULTADO                           ║
╠══════════════════════════════════════════════════════════════╣
║  100.00 USD = 85.50 EUR                              ║
║  Tasa de cambio: 1 USD = 0.8550 EUR                    ║
╚══════════════════════════════════════════════════════════════╝
```

## Monedas Soportadas

La aplicación soporta todas las divisas disponibles en la API FastForex, incluyendo:
- USD (Dólar Estadounidense)
- EUR (Euro)
- MXN (Peso Mexicano)
- GBP (Libra Esterlina)
- JPY (Yen Japonés)
- Y muchas más...

## Tecnologías Utilizadas

- **Java**: Lenguaje de programación principal
- **Gson**: Biblioteca para procesamiento de JSON
- **HTTP URL Connection**: Para conexiones a la API
- **Scanner**: Para captura de entrada del usuario
- **Unicode**: Para la interfaz visual atractiva

## Notas Importantes

- La aplicación requiere conexión a Internet para funcionar
- Las tasas de cambio se obtienen en tiempo real
- Se recomienda verificar la conectividad antes de usar la aplicación
- La API puede tener límites de uso, consulta la documentación oficial

## Solución de Problemas

### Error de Conexión
- Verifica tu conexión a Internet
- Comprueba que la API esté disponible
- Revisa si hay firewall bloqueando la conexión

### Error de Moneda
- Asegúrate de usar códigos de moneda válidos (3 letras)
- Usa la opción "Ver monedas disponibles" para ver las opciones válidas

### Error de Compilación
- Verifica que Java esté instalado correctamente
- Asegúrate de que la biblioteca Gson esté en el classpath
- Compila desde el directorio raíz del proyecto

## Contribuciones

Si encuentras algún problema o tienes sugerencias de mejora, no dudes en contribuir al proyecto.

## Licencia

Este proyecto es de uso educativo y demostrativo.
