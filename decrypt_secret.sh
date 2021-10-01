#!/bin/sh
gpg --quiet --batch --yes --decrypt --passphrase="$SSH_USER_PWD" --output settings_auth.xml settings_auth.xml.gpg