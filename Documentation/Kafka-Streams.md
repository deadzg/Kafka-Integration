# Kafka Streams


Stateless processing: Map, Filter etc

Stateful processing : Join, groupie etc


Per-record stream processing

No separate cluster needed


- StreamsConfig
	Application_id_config
	Bootstrap_server
- SerDes
- KStream : Collection of unbounded sequence of discrete facts
- KTable : Collection of evolving facts

Ktable can be converted to KStream and vice versa

KGroupedTable

State is stored in the Kafka topic

Windowing

Interactive Queries