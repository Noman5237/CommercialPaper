# Generate the orderer genesis block and its inspection
configtxgen -configPath $PWD/configs/configtx --outputCreateChannelTx $PWD/artifacts/configtx/magnetocorp-channel.tx -profile MagnetoCorpChannel -channelID magnetocorp-channel

pushd $PWD/artifacts/configtx
configtxgen -inspectChannelCreateTx magnetocorp-channel.tx > magnetocorp-channel-info.json
popd
