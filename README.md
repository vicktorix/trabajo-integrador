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
La rama _branch_ principal es develope, es la rama por defecto.<br>
Para cambiar de rama a una rama como ser _rama2_ se hace con<br>
**[1]**
```
git checkout rama2
```
en el caso de que la rama no exista y se quiera crearla puede usarse también<br>
**[2]**
```
git branch rama2
```
Cuando una rama (incluida la principal _develope_) ha tenido modificaciones por algun otro colaborador, será necesario traernos esos cambios.
En el caso de que no estemos en esa rama primer deberemos ejecutar el comando (1) y luego hacer<br>
**[3]**
```
git pull
```

Es conveniente trabajar sobre ramas ya que así no tocamos el código que esté en develope y que puede estar funcionando sin problemas

### Commits
Primero hay que agregar los archivos modificados al stage con<br>
**[4]**
```
git add -A
```
Para hacer un commit y guardar los archivos en el repositorio local hacemos<br>
**[5]**
```
git commit -m "Un mensaje que indique que cambios se realizaron"
```
Para que esos cambios estén disponibles en el repositorio remoto (Git Hub) es necesario hacer un push<br>
**[6]**
```
git push
```

### Pull Request
Para unir el código de una branch como _rama2_ con el de _develope_ se debe hacer un _pull request_ 
Seleccionamos la opción de Pull Requests
![pull_request_1](https://user-images.githubusercontent.com/3047053/229970877-cb50a84b-603d-4d3d-aecd-4190fd8edf51.png)

Luego vamos a crear un nuevo Pull Request en el botón verde New Pull Request
![pull_request_2](https://user-images.githubusercontent.com/3047053/229971040-d2a5ba32-4810-4f69-9cf9-f246a2fde369.png)

Y finalmente seleccionamos que rama o _branch_ (compare) queremos comparar con develope (base)
![pull_request_3](https://user-images.githubusercontent.com/3047053/229971202-972576d8-adc6-4059-aae4-83980c72fd08.png)

Luego nos mostrará una pantalla para ingresar una descripción de que cambio o agregados hicimos en el código y un botón de Create Pull Request.
Allí los otros desarrolladores podrán ver los cambios antes de que sean agregados a _develope_ y probar el código haciendo un checkout de esa rama usando el comando (1)
