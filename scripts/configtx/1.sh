# Generate the orderer genesis block and its inspection
configtxgen -configPath $PWD/configs/configtx -outputBlock $PWD/artifacts/configtx/magnetocorp-orderer-genesis.block -profile MagnetoCorpOrdererGenesis -channelID orderer-system-channel

pushd $PWD/artifacts/configtx
configtxgen -inspectBlock magnetocorp-orderer-genesis.block > magnetocorp-orderer-genesis-info.json
popd
