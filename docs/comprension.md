# Comprensión del diseño — Enunciado B
**Nombre:** Maddox Santiago Valbuena Ossa   
**Fecha:** 19 de octubre de 2026

Responde con tus palabras, basándote en `docs/UML-final.md`. Respuestas
concisas (2–4 líneas por pregunta). Evidencia que entendiste el contrato
**antes** de programar.

---

### 1. Herencia y polimorfismo
¿Por qué `Modelo` es una clase abstracta y no concreta? Menciona qué método
obliga a cada subclase a implementar y qué gana el sistema con eso.

> _Tu respuesta:_ "Modelo" es abstracta porque no existe un modelo general,
> siempre es CNN, RNN o Regresión. Obliga a cada hija a escribir su propio
> "entrenar()". Así, quien lo use solo llama a `modelo.entrenar()` sin importar
> de qué tipo sea.
---

### 2. Composición vs. agregación
Explica la diferencia entre `Experimento ◆— Modelo` (composición) y
`Plataforma ◇— Experimento` (agregación). ¿Qué pasaría con el modelo si se
elimina el experimento? ¿Y con los experimentos si se elimina la plataforma?

> _Tu respuesta:_ En la relación "Experimento ◆— Modelo" el modelo depende del
> experimento, ya que el modelo se crea dentro de experimento haciendo que si el experimento 
> deja de existir el modelo lo hará junto con él. Mientras que, en la relación de 
> "Plataforma ◇— Experimento" los experimentos hacen parte de la plataforma, donde 
> si la plataforma deja de existir los experimentos pueden ser evaluados o realizados
> en otra plataforma sin dejar de existir.

---

### 3. Asociación
En `Investigador — Experimento`, ¿quién es dueño de quién? ¿Por qué se modela
como N:M y no como composición?

> _Tu respuesta:_ Ninguno es dueño del otro, solo comparten una relación donde
> ambas pueden ser independientes. Así como un experimento puede tener varios
> investigadores, un investigador puede hacer muchos experimentos. Esto 
> modelado como N:M y no como composición, ya que el experiemnto tendría que
> tener un solo investigador.

---

### 4. Dependencia
¿Por qué `Modelo` **depende** de `MetricaEvaluacion` en lugar de **tenerlo**
como atributo? ¿Qué ventaja tiene pasar el servicio como parámetro?

> _Tu respuesta:_ Ya que solo se usa el Modelo cuando se evalúa o se calcula
> la métrica. Al pasar el servicio como parámetro permite cambiar la métrica 
> o el cálculo sin necesidad de modificar o cambiar el Modelo.

---

### 5. Listas
En este proyecto se usan dos estructuras distintas:
- `int[] lossPorEpoca` en `Modelo`
- `ArrayList<Experimento>` en `Plataforma`

Explica por qué en cada caso se eligió esa estructura y no la otra.

> _Tu respuesta:_ "int[]" porque el número de épocas se sabe desde el inicio
> ("maxEpocas"), es decir, ya está definida. "ArrayList" porque no se sabe 
> cuántos experimentos se van a agregar.