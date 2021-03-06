## [@bashly-upgrade completions send_completions]
send_completions() {
  echo $'# papernet completion                                      -*- shell-script -*-'
  echo $''
  echo $'# This bash completions script was generated by'
  echo $'# completely (https://github.com/dannyben/completely)'
  echo $'# Modifying it manually is not recommended'
  echo $''
  echo $'_papernet_completions() {'
  echo $'  local cur=${COMP_WORDS[COMP_CWORD]}'
  echo $'  local compline="${COMP_WORDS[@]:1:$COMP_CWORD-1}"'
  echo $''
  echo $'  case "$compline" in'
  echo $'    \'root-ca-server\'*)'
  echo $'      while read; do COMPREPLY+=( "$REPLY" ); done < <( compgen -W "--help -h init start" -- "$cur" )'
  echo $'      ;;'
  echo $''
  echo $'    \'completions\'*)'
  echo $'      while read; do COMPREPLY+=( "$REPLY" ); done < <( compgen -W "--help -h" -- "$cur" )'
  echo $'      ;;'
  echo $''
  echo $'    *)'
  echo $'      while read; do COMPREPLY+=( "$REPLY" ); done < <( compgen -W "--help --version -h -v completions root-ca-server" -- "$cur" )'
  echo $'      ;;'
  echo $''
  echo $'  esac'
  echo $'} &&'
  echo $'complete -F _papernet_completions papernet'
  echo $''
  echo $'# ex: filetype=sh'
}