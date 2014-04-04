#!/bin/bash
#
# Copyright 2004-2014 The Kuali Foundation
#
# Licensed under the Educational Community License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.opensource.org/licenses/ecl2.php
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

check_not_blank GPG_PASSPHRASE $GPG_PASSPHRASE

ACCESS_KEY=AKIAJFD5IM7IPVVUEBNA
SECRET_KEY=jA0ECQMCcSOVTI3J0Ym+0l0B3rTDQ9MBRtjvjvT6LvPxk2Aqqx/bqYPNzU42uL+EBSh//otXUlFhqnnizUm1iald6rntwAnbUxLlza463B7wu8s+J3Y00GtCm3A/bbuPCfuRnOGRDsg+t8Gfi7E=
SECRET_KEY=$(decrypt $SECRET_KEY $GPG_PASSPHRASE)
