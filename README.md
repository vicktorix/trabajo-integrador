# trabajo-integrador
## Trabajo práctico integrador de Argentina Programa 4.0, Desarrollador Java Inicial<br><br>

### Clonar el repositorio
Para clonar el repositorio usar
```
git clone https://github.com/vicktorix/trabajo-integrador.git
```

o si se definió una clave para ssh puede usarse
```
git clone git@github.com:vicktorix/trabajo-integrador.git
```

### Ramas
La rama _branch_ principal es develop, es la rama por defecto.
Para cambiar de rama a una rama como ser _rama2_ se hace con
```
git checkout rama2
```
en el caso de que la rama no exista y se quiera crearla puede usarse también
```
git branch rama2
```

Es conveniente trabajar sobre ramas ya que así no tocamos el código que esté en develop y que puede estar funcionando sin problemas

### Commits
Primero hay que agregar los archivos modificados al stage con
```
git add -A
```
Para hacer un commit y guardar los archivos en el repositorio local hacemos
```
git commit -m "Un mensaje que indique que cambios se realizaron"
```
Para que esos cambios estén disponibles en el repositorio remoto (Git Hub) es necesario hacer un push
```
git push
```

