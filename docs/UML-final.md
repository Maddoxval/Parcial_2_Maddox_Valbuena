# UML Final — Enunciado B: Plataforma de Entrenamiento de Modelos ML
### Documento de diseño — Elaborado por el docente

Este documento es **el contrato de diseño** del parcial. El código que
implementes debe respetar exactamente estas clases, atributos, métodos y
relaciones. No se permite agregar ni eliminar clases.

---

## 1. Diagrama de clases

```
                    +----------------------+
                    |   <<abstract>>       |
                    |       Modelo         |
                    +----------------------+
                    | - nombre: String     |
                    | - lossPorEpoca:int[] |
                    | - epocaActual: int   |
                    | - mejorLossRegistrado: int |
                    +----------------------+
                    | + Modelo(nombre:String, maxEpocas:int) |
                    | + entrenar(d:Dataset):double {abstract} |
                    | + registrarEpoca(loss:int):void |
                    | + mejorLoss():int    |
                    | + calcularMetrica(m:MetricaEvaluacion):double |
                    | + getNombre():String |
                    | + getLossPorEpoca():int[] |
                    | + getEpocaActual():int |
                    | + getMejorLossRegistrado():int |
                    +----------△-----------+
                               │
          ┌────────────────────┼────────────────────┐
          │                    │                    │
+-----------------+  +-----------------+  +----------------------+
|   ModeloCNN     |  |   ModeloRNN     |  |  ModeloRegresion     |
+-----------------+  +-----------------+  +----------------------+
| - numFiltros:int|  | - numCapas:int  |  | - grado:int          |
+-----------------+  +-----------------+  +----------------------+
| + ModeloCNN(nombre, maxEpocas, numFiltros) | + ModeloRNN(nombre, maxEpocas, numCapas) | + ModeloRegresion(nombre, maxEpocas, grado) |
| + entrenar(d:Dataset):double | + entrenar(d:Dataset):double | + entrenar(d:Dataset):double |
| + getNumFiltros():int | + getNumCapas():int | + getGrado():int |
+-----------------+  +-----------------+  +----------------------+

+----------------------+    ◆ (composición)     +-----------------+
|     Experimento      |◆──────────────────────>|     Modelo      |
+----------------------+                        +-----------------+
| - id: String         |
| - modelo: Modelo     |
+----------------------+
| + Experimento(id:String, tipoModelo:String, maxEpocas:int) |
| + ejecutar(d:Dataset):double |
| + getId():String |
| + getModelo():Modelo |
+----------------------+
          △
          │ ◇ (agregación) 1..*
          │
+--------------------------+
|       Plataforma         |
+--------------------------+
| - nombre: String         |
| - experimentos:ArrayList<Experimento> |
+--------------------------+
| + Plataforma(nombre:String) |
| + agregarExperimento(e:Experimento):void |
| + reporteMejorLoss():List<String> |
| + getNombre():String |
| + getExperimentos():ArrayList<Experimento> |
+--------------------------+

+------------------------+     ——— (asociación N:M)  +-----------------+
|    Investigador        |──────────────────────────>|   Experimento   |
+------------------------+                           +-----------------+
| - nombre: String       |
| - experimentos:ArrayList<Experimento> |
+------------------------+
| + Investigador(nombre:String) |
| + asociarExperimento(e:Experimento):void |
| + getNombre():String |
| + getExperimentos():ArrayList<Experimento> |
+------------------------+

+----------------+      ⇢ (dependencia)      +----------------------+
|     Modelo     |──────────────────────────>| MetricaEvaluacion    |
+----------------+   usa en calcularMetrica() +---------------------+
                                               | + calcular(modelo:Modelo, nombreMetrica:String):double |
                                               +---------------------+

+-----------------+
|     Dataset     |
+-----------------+
| - nombre: String |
| - cantidadMuestras: int |
+-----------------+
| + Dataset(nombre, cantidadMuestras) |
| + getNombre():String |
| + getCantidadMuestras():int |
+-----------------+
```

---

## 2. Las cinco relaciones — justificación

### 2.1 Herencia — `Modelo` → `ModeloCNN` / `ModeloRNN` / `ModeloRegresion`
Los tres tipos **son** modelos de ML. Comparten nombre, historial de
loss por época y mejor loss, pero difieren en cómo entrenan y en sus
hiperparámetros. Se modela con clase abstracta `Modelo` y método abstracto
`entrenar(Dataset)`.

### 2.2 Composición — `Experimento ◆— Modelo`
El modelo **pertenece** al experimento: se crea dentro del constructor de
`Experimento` según el tipo solicitado y no tiene vida útil propia fuera de él.
Si el experimento se destruye, el modelo también. Se implementa creando el
modelo **dentro** del constructor de `Experimento`.

### 2.3 Agregación — `Plataforma ◇— Experimento`
Los experimentos **pueden existir** sin la plataforma. Se crean
independientemente y luego se registran en la plataforma con
`agregarExperimento()`. La plataforma solo mantiene referencias en su
`ArrayList<Experimento>`.

### 2.4 Asociación — `Investigador — Experimento` (N:M)
Un investigador lanza varios experimentos y un experimento puede tener varios
investigadores colaborando. No hay propiedad; solo referencia mutua por
atributo (`ArrayList<Experimento>`).

### 2.5 Dependencia — `Modelo ⇢ MetricaEvaluacion`
`Modelo` **usa** `MetricaEvaluacion` en un método puntual
(`calcularMetrica`), pero no lo guarda como atributo. Se pasa como parámetro.

---

## 3. Reglas de negocio implícitas

Estas reglas no se derivan del UML, pero los tests JUnit las validan:

1. `Experimento(id, tipoModelo, maxEpocas)` crea el modelo según el tipo:
   - `"CNN"` → `ModeloCNN`
   - `"RNN"` → `ModeloRNN`
   - `"REGRESION"` → `ModeloRegresion`
   - Cualquier otro valor → `modelo = null` y no se entrena nada.
2. `Modelo.registrarEpoca(int)` respeta la capacidad del arreglo
   `lossPorEpoca`. Si ya se llenó, **no** agrega más épocas pero **sí**
   actualiza `mejorLossRegistrado` con el mínimo.
3. `Modelo.mejorLoss()` retorna `Integer.MAX_VALUE` si no se ha registrado
   ninguna época.
4. `Modelo.calcularMetrica(MetricaEvaluacion)` delega en
   `metrica.calcular(this, "accuracy")`.
5. `MetricaEvaluacion.calcular(modelo, nombre)` retorna `100.0 / (1 + mejorLoss)`
   o `0` si `mejorLoss == Integer.MAX_VALUE`.
6. `Investigador.asociarExperimento(...)` no debe agregar el mismo
   experimento dos veces.

---

## 4. Firmas exactas de los métodos

Las firmas están dadas en el código base de `src/`. Tu tarea es implementar
sus cuerpos, **no cambiar sus firmas**.

| Clase | Método | Retorno esperado |
|---|---|---|
| `Modelo` | `registrarEpoca(int)` | `void` |
| `Modelo` | `mejorLoss()` | `int` |
| `Modelo` | `calcularMetrica(MetricaEvaluacion)` | `double` |
| `ModeloCNN` | `entrenar(Dataset)` | `double` |
| `ModeloRNN` | `entrenar(Dataset)` | `double` |
| `ModeloRegresion` | `entrenar(Dataset)` | `double` |
| `Experimento` | constructor `(String, String, int)` | — |
| `Experimento` | `ejecutar(Dataset)` | `double` |
| `Plataforma` | `agregarExperimento(Experimento)` | `void` |
| `Plataforma` | `reporteMejorLoss()` | `List<String>` |
| `Investigador` | `asociarExperimento(Experimento)` | `void` |
| `MetricaEvaluacion` | `calcular(Modelo, String)` | `double` |

---

## 5. Lo que NO debes hacer

- No agregar clases nuevas.
- No cambiar firmas de métodos existentes.
- No modificar la carpeta `test/`.
- No convertir atributos privados en públicos.
- No duplicar lógica de entrenamiento en `Experimento` (debe delegar al
  modelo mediante polimorfismo).
- No hacer que `Experimento` reciba el modelo ya instanciado desde afuera
  (rompe la composición).