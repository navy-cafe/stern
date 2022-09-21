# JsonRelayServer

## Endpoints

<details>
<summary><code>GET /servers</code>: returns a list of all servers</summary>

Returns a list of servers. Supports the following filters:

```json
// filter by specific server types
{
  "type": "serverType",
  "serverType": "SOMETHING"
}

// filter by a fuzzy-searched ID value
{
  "type": "id",
  "id": "id"
}
```

<details>
<summary>Example <code>GET /servers</code> request</summary>

```json
{
  "filters": [
    {
      "type": "serverType",
      "serverType": "LOBBY"
    }
  ]
}
```

</details>

<details>
<summary>Example <code>GET /servers</code> response</summary>

```json
{
  "servers": [
    {
      "id": "lobby-1",
      "uuid": "xxx",
      "host": "localhost",
      "port": "25565",
      "serverType": "LOBBY"
    },
    {
      "id": "lobby-2",
      "uuid": "xxx",
      "host": "localhost",
      "port": "25566",
      "serverType": "LOBBY"
    }
  ]
}
```

</details>
</details>