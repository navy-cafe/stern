# stern

A high-level, abstracted backend for networks of (game) server instances.

---
- [the backend](#the-backend)
- [the relays](#the-relays)
  - [relays/json](#relaysjson)
  - [relays/redis](#relaysredis)
  - [relays/websockets](#)
  - [relays/grafana](#relaysgrafana)
- [the targets](#the-targets)
  - [targets/docker](#targetsdocker)
  - [targets/pterodactyl](#targetspterodactyl)
  - [targets/kubernetes](#targetskubernetes)
- [the clients](#the-clients)
  - [clients/discord](#clientsdiscord)
  - [clients/paper](#clientspaper)
  - [clients/velocity](#clientsvelocity)
---

stern is a high-level server manager for multiplayer games.

stern picks a set of abstractions over orchestration systems  and messaging layers, providing a flexible, scalable base
for running a network's business logic.

stern can conform to a variety of use cases, with first-party support for:

- [Docker](), [Pterodactyl]() and (maybe) [Kubernetes]() targets for running server instances
- [Redis](), [WebSockets](), and [JSON]() relays as a communication layer
- [Discord bot]() and [Paper plugin]() clients for third-party software integrations

<details>
<summary>Example scenario with stern and a Minecraft network</summary>

> Imagine a fledgling Minecraft server looking to utilize a network of servers for some purpose (minigames, instanced worlds, lobbies, etc..).
> 
> Perhaps they decide using Docker containers is their best choice, but they don't have a system to interact with Docker to automatically create servers under certain conditions (i.e. too many players, need new lobby instances).
> 
> With stern, the developers of the server could utilize the [Docker target](#targetsdocker), along with the [Velocity](#clientspaper)
> and [Paper](#clientspaper) clients running on their instances, to keep track of their servers and easily add and remove instances.
> 
> They could implement simple load-balancing logic that integrates with the Velocity client, requesting new servers as needed, and
> rely on stern's logic to handle Velocity's server registry and player connections.

</details>

# the backend

stern's backend application acts as the central source-of-truth for the server instance registry.
In simpler terms, the backend is responsible for:

- maintaining a list of servers, their state (online/offline status, useful logging information, etc)
- acting as the middle-man between a client's request and a target's response
  - for example: client tells stern "create server of type `LOBBY` with ID `abcdefg`, and stern relays this request to the configured target (Docker or Kubernetes)
- sending/receiving server status requests/changes

To accomplish these goals, the structure of stern is split up into three core concepts:

- [the relays](#the-relays)
- [the targets](#the-targets)
- [the clients](#the-clients)

The core logic behind stern depends on these concepts, and _not_ any specific technology.
As such, if a first-party stern module doesn't fit your needs, take inspiration from a pre-existing module and write your own!

# the relays

A relay is simply an interface for third-party services to interact with the stern backend.
A relay's code is split up into `client` and `server` packages -- `client` contains code used to connect to the relay,
and `server` contains code used to provide the relay. This allows a relay to be easily used by both the backend and any
client implementations.

For example, the JSON relay provides an HTTP API to create, destroy, and inspect server instances. The Redis
relay can be used to receive live updates on server status changes (useful for using a Minecraft proxy such as [Velocity](https://velocitypowered.com/)).

### relays/json

The JSON relay provides an HTTP API to expose server information to consumers.

### relays/redis

The Redis relay pushes live server status changes to a Redis instance over pub-sub.

### relays/grafana

The Grafana relay pushes live server status information to a Grafana dashboard. This relay does not expose any APIs
on its own, and should be used in conjunction with other relays.

# the targets

A target is an interface between the backend and a system that can host server instances.

### targets/docker

The Docker target can be used to interact with a Docker socket and deploy servers as Docker containers.

### targets/kubernetes

### targets/pterodactyl

### targets/host

# the clients

The stern project supports a handful of first-party client implementations.
These are applications that run separate from a backend instance, and provide additional functionality tailor-made to a specific use case.
If you find a client that does not meet your requirements, feel free to PR, create an issue, or write your own!

### clients/discord

The Discord client for stern provides a bot that can be used to inspect a live stern instance. Additionally, commands
to initialize and destroy server templates can be used.

###  clients/paper

The Paper client for stern provides a plugin that can be installed to a supported Paper server.

The plugin listens for server events such as startup, shutdown, and pushes this information to a stern relay.

### clients/velocity

The Velocity client for stern provides a plugin that can be installed to a supported Velocity server.

The plugin loads server instance information from stern, automatically populating the proxy's server registry with the
stern network's latest changes.

