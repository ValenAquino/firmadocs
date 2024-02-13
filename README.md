# Firmadocs
Firmar y enviar documentos en papel es (o debería ser) algo del pasado: además de tener que usar papel e imprimir, hay que firmarlo a mano y enviarlo por correo postal. ¡No sólo es tedioso sino costoso y poco ecológico! Y ni hablar cuando el documento tiene que ser firmado por personas que están a grandes distancias. Por eso creamos Firmadocs, una plataforma en español de firma electrónica en la que cualquier persona puede subir sus documentos, completarlos y firmarlos colaborativamente. No importa si se trata de un contrato, una carta de recomendación, o una declaración jurada, ¡Firmadocs es para vos! 

Así es como Dani, de Firmadocs, comenzó a presentar con mucha pasión su producto al equipo de la consultora 2Diseños, que se encargará de desarrollarlo. Aunque la sala no quedó tan emocionada, se apresuró a preguntar sobre los requerimientos. 

# Contexto
### Documentos
En Firmadocs podés iniciar y participar en procesos de firma colaborativa. Para comenzar un proceso de firma colaborativa de un documento, vas a necesitar primero subir un PDF (el documento en cuestión) y luego compartirlo, agregando colaboradores al mismo. 

Cuando agregás un colaborador, tenés que especificar si se trata de un firmante o un lector. Los lectores **deberán** ingresar al documento para **leerlo** e **indicar que lo hicieron**, mientras que los firmantes **deberán** cargar sus datos y firma. Es importante que debe quedar registro de quienes completaron su acción correspondiente; si alguno de los colaboradores no lo hizo, el proceso de firma no puede ser finalizado.  

Además, antes de comenzar a agregar colaboradores, deberás indicar si firmarán o leerán en orden indistinto u orden prefijado.

* **Orden indistinto:** cada colaborador recibe la notificación al mismo tiempo y puede realizar su acción a partir de ese momento;
* **Orden prefijado:** cada colaborador recibe la notificación en orden. Un colaborador es notificado y puede realizar su acción recién cuando el anterior ha concluido la suya.

Una vez que el proceso de firma haya sido configurado, con todos sus colaboradores asignados, podrás liberar el documento, notificando a los colaboradores que corresponda.

A medida que cada colaborador sea notificado, podrá ingresar al sistema, buscar el documento entre los que le fueron compartidos, y realizar su acción. No podrá realizar otra que no le corresponda, ni antes de que le sea habilitada. Tampoco podrá ver los documentos compartidos si aún no es su turno. 

Cuando el último colaborador complete su acción, el proceso de firma será marcado automáticamente como finalizado.  

### Notificaciones
Es importante que todas las personas sean notificadas a través de medios que elijan (correo electrónico, Whatsapp, notificación en aplicación, SMS, entre otros, configurables por usuario) sobre los distintos eventos que van ocurriendo, incluyendo: 
* Cuando se te comparte un documento
* Cuando un colaborador de un proceso de firma que iniciaste completa su acción
* Cuando un proceso de firma que iniciaste culminó
* **Diariamente**, cuando sos un colaborador y no completaste tu acción.

### Firma electrónica

Cada vez que un firmante firma un documento, al mismo debe anexarse un código hexadecimal único que representa su firma electrónica y demuestra su validez. Este código debe ser generado usando un componente externo en ese preciso momento, ni antes ni después.  

![Interfaz del GeneradorDeFirma](https://www.plantuml.com/plantuml/img/7SZ12O0m30NGUwVuHuLEu5bq02U8zYk1Df7GcxYxu3i-jSuuvI1M6aEAnyn5emcU15n_nX-3UTk36NApLYsUmIPQCpehJpSVCM7heNQbzmC0)

### Planes de facturación

Firmadocs permite a les usuaries contratar un plan acorde a sus necesidades: se cuenta con planes plata, bronce y oro, que permiten iniciar hasta 5, 20 y 100 procesos de firma mensuales. Una vez que se agotó el plan, **no se debe permitir generar nuevos procesos de firma más hasta el mes siguiente.**

## Requerimientos detallados

1) Como iniciador, deseo poder iniciar el proceso de firma de un documento, indicando el PDF.
2) Como iniciador, deseo poder especificar si el proceso de firma tendrá orden o será de orden indistinto.
3) Como iniciador, deseo poder asignarle colaboradores a un proceso de firma, especificando si serán lectores o firmantes.
4) Como iniciador, deseo poder liberar un proceso de firma, enviando notificaciones a cada uno de los colaboradores, en orden (o a todos a la vez, si es orden indistinto).
5) Como colaborador, deseo poder listar los procesos de firma que tengo compartidos.
6) Como iniciador o colaborador, debe poder listar las personas que leyeron el documento al momento actual del proceso de firma
7) Como iniciador o colaborador, debe poder listar las personas que firmaron el documento al momento actual del proceso de firma
8) Como firmante, deseo poder firmar un documento del que sea colaborador de su proceso.
9) Como lector, deseo poder marcar como leído un documento del que sea colaborador de su proceso
10) Como colaborador, deseo recibir recordatorios cuando no haya realizado mi acción.
11) Como colaborador, deseo poder anular un proceso de firma no liberado. A nivel facturación del plan no se contabilizará.
12) Como colaborador, deseo poder anular un proceso de firma ya liberado. A nivel facturación del plan sí se contabilizará.