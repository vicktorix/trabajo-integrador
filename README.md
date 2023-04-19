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
git push --set-upstream origin rama2
```
donde a rama2 lo deberemos reemplazar con el nombre de la rama que hayamos creado

### Pull Request
Para unir el código de una branch como _rama2_ con el de _develope_ se debe hacer un _pull request_ 
Seleccionamos la opción de Pull Requests
![pull_request_1](https://user-images.githubusercontent.com/3047053/229970877-cb50a84b-603d-4d3d-aecd-4190fd8edf51.png)

Luego vamos a crear un New Pull Request en el botón verde New Pull Request
![pull_request_2](https://user-images.githubusercontent.com/3047053/229971040-d2a5ba32-4810-4f69-9cf9-f246a2fde369.png)

Y finalmente seleccionamos que rama o _branch_ (compare) queremos comparar con develope (base)
![pull_request_4](https://user-images.githubusercontent.com/3047053/229977587-27822b69-6d1c-4240-92b3-540c4f939dbd.png)

Luego nos mostrará una pantalla para ingresar una descripción de que cambio o agregados hicimos en el código y un botón de Create Pull Request.
![pull_request_5](https://user-images.githubusercontent.com/3047053/229977637-47f07c10-f85b-4cce-801e-3973006b7fe6.png)
Se hace click en el botón _Create pull request_ y nos muestra la siguiente pantalla.
En _Asignees_ nos asignamos el PR a nuesto usuario de Github y de nuevo en el botón _Create pull request_ se hace click. El Pull Request se ha creado y se puede avisar a los demás programadores para que vean y prueben el nuevo código. También en el cuadro a donde explicamos que cambios introduce el PR se puede explicar como se hace para probar el funcionamiento del código. 
![pull_request_6](https://user-images.githubusercontent.com/3047053/229977661-d79b1f4c-a4c3-472c-a402-a0538d6ad8b8.png)

Una vez que los otros programadores han probado y aprobado los cambios se puede hacer el _merge_ del pull request (botón verde)
![pull_request_7](https://user-images.githubusercontent.com/3047053/229978418-6576f208-db67-45bc-99ce-dbc536ddbd8e.png)

Al hacer click habrá una segunda confirmación, se hace click en _Confirm merge_ y el código pasa de la rama que creamos a la rama principal de _develope_
![pull_request_8](https://user-images.githubusercontent.com/3047053/229978527-7522bb3f-37d5-4679-b316-93d3722bfa66.png)

Finalmente eliminamos la rama que usamos para hacer el pull request ya que todo el código ahora está en la rama principal
![pull_request_9](https://user-images.githubusercontent.com/3047053/229978692-1f7417c9-fb80-4bcb-898b-44151c6fc0fb.png)
