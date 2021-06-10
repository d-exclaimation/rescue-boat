
# Rescue Boat

Rescue files and preserve their natural positions, so you can delete unfinished project without having to manual copy paste stuff




## Features

Rescue boat perform 2 task, encapsule files into boats or tubes and rescue file back using `.rescuetag`

### Deploying / Saving the files

#### Deploy a boat

```bash
Boat, Tube, or Rescue (b/t/r)? [Boat|b]
>? <Indices> | <List All>
// And then keep answering for files or just the simplified list notation
```

| Responses | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `Indices` | `Int / Int[]` | Give files to be boated |
| `List all`| `String: L`| List all files under the current directory |

#### Deploy a tube

```bash
Boat, Tube, or Rescue (b/t/r)? [Tube|t]
>? <Index> | <List All>
// And answer with the correct index
```

| Responses | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `Index` | `In` | Give file to be tubed |
| `List all`| `String: L`| List all files under the current directory |


Both command will ask for recovery directory and create a `.rescuetag` file to 
be used in retriving

### Rescuing / Retriving the files

#### Rescue files

```bash
Boat, Tube, or Rescue (b/t/r)? [Rescue|r]
Select the .rescuetag file? <Rescue tag>
```

| Responses | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `Rescue tag` | `In` | Path to a `.rescuetag` |

  
## Deployment

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

Install script maybe coming in the future

  
## FAQ

#### Why?

I have made projects myself, and a lot of times I have created files and/or codes that are good and works well but I want to scraped the project or the entire structure of it.
This puts me in a position where I have scramble in manually copy pasting files which can get annoying when I want to preserve the directory structures.


#### Why Kotlin?

I thought it would be fun, Kotlin is statically typed, object-oriented and functional programming language that runs on JVM. 
It is very convienient using this to take advantage of the massive Java ecosystem in a more functional way.
I think I want to use it especially when cocurrency is the primary goal which what I have been focusing on with Elixir and Go. 
I don't have projects in mind yet for Kotlin, and since this is a very small one. I think I can just use whatever. Technically, Go is much better fit for CLIs.

#### Why not Go, Elixir, Typescript, Rust?

- Rust: I only need filesystem access. This is a bit overkill for tools
- Elixir: I don't need any cocurrenct process and that I have planned more projects with Elixir
- Go: This is probably the best option, but for fun, I decided not to use it
- Typescript, convienient but will only work well with Node projects unless I want to make binaries

