# Plataforma9-34
La empresa Plataforma 9-3/4 desea implementar una herramienta en línea para la venta de 
pasajes de ómnibus de larga distancia y nos ha encargado su desarrollo.
Para poder usar el sitio, y cumpliendo con las reglamentaciones de la Comisión Nacional de 
Regulación de Transporte (CNRT), el pasajero debe registrarse con sus datos personales 
(apellido, nombre y DNI). El DNI del pasajero será utilizado como identificador único en el 
sistema. Por seguridad, durante la registración el sistema solicitará una clave de acceso la cual 
tiene que estar compuesta por 8 caracteres como mínimo, incluyendo al menos un carácter en 
minúscula, uno en mayúscula y uno que sea número. En ese momento, el pasajero puede 
asociar una tarjeta de crédito donde se cargarán los pagos de los pasajes.
Para comprar un pasaje, el pasajero debe establecer el origen y el destino y el día deseado de 
viaje. Luego, el sistema buscará en los sistemas de cada empresa de transporte asociada los 
servicios que cumplen con la búsqueda del pasajero, las características del ómnibus, los 
asientos libres y ocupados y el recorrido intermedio. El sistema listo al pasajero todos los 
servicios con disponibilidad, con el nombre de la empresa y las horas de salida y llegada. El 
pasajero podrá entonces filtrar estos resultados por una empresa en particular, por un margen 
de horas de llegada y/o salida, y por costo del pasaje. Al seleccionar un servicio, se le muestra 
una vista esquemática del ómnibus, resaltando los asientos disponibles. El pasajero puede 
seleccionar uno o varios lugares a ocupar, y luego pasar a completar la compra. Al momento de 
completar la compra, el pasajero debe completar los datos de su tarjeta de crédito. Si tiene una 
tarjeta asociada, se cargarán los campos: número de tarjeta, banco emisor y marca de tarjeta 
de crédito. En caso contrario, el pasajero debe completarlos manualmente. Si seleccionó más 
de un asiento, debe asociar a cada uno el número de documento del pasajero que lo ocupará. 
El sistema verificará si existe tal documento en el sistema. De ser así, el sistema completará el 
nombre y apellido correspondiente y notificará al pasajero correspondiente. En caso contrario, 
el pasajero deberá completar los campos nombre y apellido.
Al estar completos los datos de la tarjeta y de los pasajeros, se mostrará el resumen de la 
misma y el pasajero podrá confirmar la compra. El sistema cargará a la tarjeta el costo del 
pasaje, cargará en el sistema de la empresa de transporte la ocupación de los asientos en el 
servicio y enviará a la casilla de correo del pasajero los pasajes para imprimir.
La empresa Plataforma 9-3/4 convino una estrategia con las empresas de transporte El Gorrión 
y Langueyú para reducir el número de asientos vacíos en los viajes. Para esto, seis horas antes 
de la salida de un ómnibus que tenga más de 20 asientos libres, se avisará a los interesados la 
venta del pasaje con 50% de descuento. Los pasajeros que estén interesados en estas 
promociones, deben suscribirse con anterioridad mediante una opción llamada “Viaje 
improvisado”, señalando el origen y destino, para recibir las alertas por correo electrónico. 
Como los pasajeros de ómnibus suelen realizar el mismo trayecto periódicamente, por trabajo o 
estudio, puede suscribirse a estas alertas al comprar un pasaje.
Una estrategia propia de Plataforma 9-¾ será proporcionar estadísticas sobre los servicios. 
Para ello, se valdrá de los celulares de los pasajeros. Aquellos pasajeros dispuestos a 
participar en la generación de estadísticas, deberán ingresar a la página de Plataforma 9-¾ en 
el navegador de su celular al momento de ascender al ómnibus y, estando logueado, 
seleccionar la opción “seguir”. En ese momento, el sistema recabará la información del pasaje 
comprado por ese pasajero que coincida con la hora de activación y comenzará a enviar al 
servidor la posición señalada por el gps cada 10 minutos. Al llegar a destino, el pasajero debe 
detener el envío seleccionando “dejar de seguir”. Si olvida seleccionar esta opción, el sistema 
automáticamente dejará de emitir la información del gps cuando se cumpla el 130% del tiempo 
estimado de viaje. Para estimular que los pasajeros usen esta funcionalidad, se le dará $10 de 
crédito que se acumularan en su cuenta para ser descontados al momento de la compra. La 
duración promedio del servicio del último mes será mostrada al momento de la búsqueda.
Finalmente, el pasajero podrá devolver pasajes, cumpliendo con las normas de la CNRT. La 
normativa autoriza el porcentaje de devolución, según la antelación a la fecha del viaje en que 
hace devolución: Más de 48 hs., se devuelve el 90% del monto abonado, entre 24 y 48 hs., el 
80% del monto abonado y entre 24 hs. y una hora antes de la salida del vehículo, 70% del 
monto abonado. En particular, Plataforma 9-¾ deducirá de la devolución un 5% por gastos 
administrativos. El sistema cargará la devolución a la tarjeta de crédito con que se realizó el 
pago del o los pasajes y informara al sistema de la empresa de ómnibus.

Objetivo del trabajo
Los objetivos del presente trabajo son que el alumno:
● pueda aplicar de forma práctica conceptos de Scrum en el desarrollo del TP.
● utilice UML para el modelado de sistemas.
