
# inspect_args
# delete all files in the $FABRIC_CA_SERVER_HOME directory except fabric-ca-server-config.yaml
pushd $FABRIC_CA_SERVER_HOME
find . ! -name 'fabric-ca-server-config.yaml' -type f -exec rm -rf {} +
popd
fabric-ca-server ${args[action]}