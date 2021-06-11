
# Rescue Boat

Rescue files and preserve their natural positions, so you can delete unfinished project without having to manual copy paste stuff




## Features

Rescue boat perform 2 task, encapsule files into boats or tubes and rescue file back using `.rescuetag`

### Deploying / Saving the files

#### Deploy a boat

```
Boat, Tube, or Rescue (b/t/r)? b
>? [indices] | [list-all]
```

| Responses | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `indices` | `integer or integers` | Indices of files to be boated |
| `list-all`| `the letter L`| List all files under the current directory |

**Note** to finish selectiong, just give an invalid input i.e nothing (enter)

#### Deploy a tube

```bash
Boat, Tube, or Rescue (b/t/r)? t
>? [index] | [list-all]
```

| Responses | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `index` | `integer` | Index of file to be tubed |
| `list-all`| `the letter L`| List all files under the current directory |


Both command will ask for recovery directory and create a `.rescuetag` file to 
be used in retriving

### Rescuing / Retriving the files

#### Rescue files

```bash
Boat, Tube, or Rescue (b/t/r)? r
Select the .rescuetag file? [rescue-tag-path]
```

| Responses | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `rescue-tag-path` | `file path` | Path to a `.rescuetag` |

  
## Build and Deployment

The project is using gradle.

To deploy, build, or use this project 

Clone the source code
```bash
git clone https://github.com/d-exclaimation/rescue-boat.git
```
Build by running

```bash
gradle installDist
```

or get distributions with

```bash
gradle build
```

_Install script maybe coming in the future or actual binaries, but I made no promises_

  
## FAQ

#### Why?

I have made projects myself, and a lot of times I have created files and/or codes that are good and works well but I want to scraped the project or the entire structure of it.
This puts me in a position where I have scramble in manually copy pasting files which can get annoying when I want to preserve the directory structures.


#### Why Kotlin?

I thought it would be fun, Kotlin is statically typed, object-oriented and functional programming language that runs on JVM. 
It is very convienient using this to take advantage of the massive Java ecosystem in a more functional way.
I think I want to use it especially when cocurrency is not the primary goal. Cocurrency is definitely a big topic I want to dive more into that's why I choose Go, and Kotlin, but apps I made might not need that level of capabilities and some sacrifice can be made to either get a larger ecosystem (larger than Elixir) or to better user experience (more features than Go)
I don't have projects in mind yet for Kotlin, and since this is a very small one. I think I can just use whatever. Technically, Go is much better fit for CLIs.

#### Why not Go, Elixir, Typescript, Rust?

- Rust: I only need filesystem access. This is a bit overkill for tools
- Elixir: I don't need any cocurrenct process and that I have planned more projects with Elixir
- Go: This is probably the best option, but for fun, I decided not to use it
- Typescript, convienient but will only work well with Node projects unless I want to make binaries

